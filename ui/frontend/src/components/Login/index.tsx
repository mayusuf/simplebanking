/** node library
 */
import { useFormik } from "formik";
import { NavLink, useNavigate } from "react-router-dom";

/** custom library
 */
import "./index.scss";
import { data_signin, request_signin } from "../../utility/data";
import { getYupSchema } from "../../utility";
import { login } from "../../apis/services/login.service";
import { Button } from "../elements/button";
import { Input } from "../elements/input";
import { useAppDispatch } from "../../redux/hooks";
import { addUser, User } from "../../redux/usersSlice";

export const Login = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  // const { saveAuthentication } = useAuthentication();

  const handleRegister = (event: any) => {
    setTimeout(() => {
      navigate("/register", { replace: true });
    }, 1);
  };

  const formik = useFormik({
    initialValues: request_signin,
    validationSchema: getYupSchema(data_signin),
    onSubmit: async (values, { setStatus, setSubmitting }) => {
      try {
        const { data: authentication } = await login(
          values.username,
          values.password
        );
        const userData: User = {
          ...authentication,
          id: 0,
          name: values.username,
        };

        dispatch(addUser(userData as User));
        //   saveAuthentication(authentication);

        setTimeout(() => {
          navigate("/", { replace: true });
        }, 1000);
      } catch (error) {
        setStatus("The login details is incorrect");
        setSubmitting(false);
      }
    },
  });

  return (
      <div className='row justify-content-center'>
        <div className='col-12 w-50 '>
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
                  <h5 className="card-title">Login</h5>
                  <hr/>
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
                        type="password"
                        id="password"
                        label="Password"
                        value={formik.values["password"]}
                        onChange={formik.handleChange}
                        error={
                            formik.touched["password"] &&
                            Boolean(formik.errors["password"])
                        }
                        helperText={
                            formik.touched["password"] && formik.errors["password"]
                        }
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

                    <div className="mt-3">
                      <Button type="submit">Login</Button>
                    </div>
                    <hr/>
                  </form>

                  <p className="mt-4 text-xs font-weight-light text-center text-muted">
                    Don't have an account?
                    <NavLink
                        to="messages"
                        className="mx-2 font-weight-medium text-dark hover:underline"
                        onClick={handleRegister}
                    >
                      Sign up
                    </NavLink>
                  </p>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>

  );
};
