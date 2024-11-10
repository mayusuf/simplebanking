/** node modules
 */
import * as yup from "yup";

/** start
 */

function createYupSchema(config) {
  const { validationType, validations = [] } = config;
  if (!yup[validationType]) {
    return null;
  }

  if (validationType === "object") {
    const { fields = [] } = config;

    return getYupSchema(fields);
  } else {
    let validator = yup[validationType]();

    validations.forEach((validation) => {
      const { params, type } = validation;
      if (!validator[type]) {
        return;
      }
      validator = validator[type](...params);
    });

    return validator;
  }
}

function getChildSchema(data_object) {
  let formSchemaObject = {};

  // eslint-disable-next-line array-callback-return
  data_object.map((each) => {
    Object.assign(formSchemaObject, {
      [each.data]: createYupSchema(each),
    });
  });

  return formSchemaObject;
}

export function getYupSchema(data_object) {
  let formSchemaObject = getChildSchema(data_object);

  const modalSchema = yup
    .object()
    .shape({ ...formSchemaObject }, [[...Object.keys(formSchemaObject)]]);

  return modalSchema;
}
