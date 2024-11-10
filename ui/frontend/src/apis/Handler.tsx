/** node library
 */

import { AuthenticationModel } from "../types/login";

/** custom library
 */

/** start
 */

const AUTHENTICATION_LOCAL_STORAGE_KEY = "app-token";

export const getAuthentication = (): AuthenticationModel | undefined => {
  if (!localStorage) {
    return;
  }

  const authenticationToken: string | null = localStorage.getItem(
    AUTHENTICATION_LOCAL_STORAGE_KEY
  );
  if (!authenticationToken) {
    return;
  }

  try {
    const authenticationObject: AuthenticationModel = JSON.parse(
      authenticationToken
    ) as AuthenticationModel;
    if (authenticationObject) {
      // You can easily check authentication_token expiration also
      return authenticationObject;
    }
  } catch (error) {
    console.error("AUTHENTICATION LOCAL STORAGE PARSE ERROR", error);
  }
};

export const setAuthentication = (
  authenticationObject: AuthenticationModel
) => {

  if (!localStorage) {
    return;
  }

  try {
    const authenticationToken = JSON.stringify(authenticationObject);
    localStorage.setItem(AUTHENTICATION_LOCAL_STORAGE_KEY, authenticationToken);
  } catch (error) {
    console.error("AUTHENTICATION LOCAL STORAGE SAVE ERROR", error);
  }
};

export const removeAuthentication = () => {
  if (!localStorage) {
    return;
  }

  try {
    localStorage.removeItem(AUTHENTICATION_LOCAL_STORAGE_KEY);
  } catch (error) {
    console.error("AUTHENTICATION LOCAL STORAGE REMOVE ERROR", error);
  }
};

export function setupAxios(axios: any) {
  axios.defaults.headers.Accept = "application/json";

  axios.interceptors.request.use(
    (config: { headers: { Authorization: string } }) => {
      const auth = getAuthentication();

      if (auth && auth.token) {
        config.headers.Authorization = `Bearer ${auth.token}`;
      }
      return config;
    },
    (err: any) => Promise.reject(err)
  );
}
