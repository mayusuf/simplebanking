/** node library
 */
import { useFormik } from "formik";
import { NavLink, useNavigate } from "react-router-dom";

/** custom library
 */
import "./index.scss";
import { data_register, request_register } from "../../utility/data";
import { getYupSchema } from "../../utility";
import { register } from "../../apis/services/login.service";
import { Button } from "../elements/button";
import { Input } from "../elements/input";

export const Register = () => {
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: request_register,
    validationSchema: getYupSchema(data_register),
    onSubmit: async (values, { setStatus, setSubmitting }) => {
      try {
        const { data: authentication } = await register(
          values.username,
          values.firstName,
          values.lastName,
          values.email,
          values.mobile
        );

        setTimeout(() => {
          navigate("/login", { replace: true });
        }, 1000);
      } catch (error) {
        setStatus("The login details is incorrect");
        setSubmitting(false);
      }
    },
  });

  return (
    <div className="position-relative d-flex flex-column justify-content-center min-vh-100 overflow-hidden">
      <div className="w-100 p-3 m-auto bg-white rounded shadow-lg border border-blue-500 lg-max-w-lg">
        <div className="m-auto d-flex justify-content-center align-items-center">
          <img
            className="d-inline-block h-100 w-auto mx-auto"
            src={"logo"}
            alt=""
          ></img>
        </div>
        <div className="card">
          <div className="card-body">
            <h5 className="card-title">Register</h5>
            <hr />
            <form noValidate onSubmit={formik.handleSubmit} className="mt-3">
              <Input
                type="text"
                id="username"
                label="Username"
                value={formik.values["username"]}
                onChange={formik.handleChange}
                error={
                  formik.touched["username"] &&
                  Boolean(formik.errors["username"])
                }
                helperText={
                  formik.touched["username"] && formik.errors["username"]
                }
              />
              <Input
                type="text"
                id="firstName"
                label="FirstName"
                value={formik.values["firstName"]}
                onChange={formik.handleChange}
                error={
                  formik.touched["firstName"] &&
                  Boolean(formik.errors["firstName"])
                }
                helperText={
                  formik.touched["firstName"] && formik.errors["firstName"]
                }
              />
              <Input
                type="text"
                id="lastName"
                label="LastName"
                value={formik.values["lastName"]}
                onChange={formik.handleChange}
                error={
                  formik.touched["lastName"] &&
                  Boolean(formik.errors["lastName"])
                }
                helperText={
                  formik.touched["lastName"] && formik.errors["lastName"]
                }
              />
              <Input
                type="text"
                id="email"
                label="Email"
                value={formik.values["email"]}
                onChange={formik.handleChange}
                error={
                  formik.touched["email"] && Boolean(formik.errors["email"])
                }
                helperText={formik.touched["email"] && formik.errors["email"]}
              />
              <Input
                type="text"
                id="mobile"
                label="Mobile"
                value={formik.values["mobile"]}
                onChange={formik.handleChange}
                error={
                  formik.touched["mobile"] && Boolean(formik.errors["mobile"])
                }
                helperText={formik.touched["mobile"] && formik.errors["mobile"]}
              />

              {formik.isSubmitting && (
                <div className="flex justify-center">
                  <div className="lds-roller">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                  </div>
                </div>
              )}
              <hr />
              <div className="mt-3">
                <Button type="submit">Register</Button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
