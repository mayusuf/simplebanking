/** node library
 */
import axios from "axios";

/** custom library
 */
import * as url_config from "../URLs";
import { AuthenticationModel } from "../../types/login";

/** start
 */

export function login(username: string, password: string) {
  return axios.post<AuthenticationModel>(url_config.default.signing, {
    username,
    password,
  });
}

export function register(
  username: string,
  firstName: string,
  lastName: string,
  email: string,
  mobile: string
) {
  return axios.post<AuthenticationModel>(url_config.default.registration, {
    username,
    firstName,
    lastName,
    email,
    mobile,
  });
}
