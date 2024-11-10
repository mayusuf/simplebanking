import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import Error from "../components/Error";
import AddAccount from "../components/AddAccount";
import Withdraw from "../components/Withdraw";
import { Login } from "../components/Login";
import Deposit from "../components/Deposit";
import Home from "../components/Home";
import { Register } from "../components/Login/Register";
import AccountDetail from "../components/AccountDetail";
import Dashboard from "../components/Dashboard";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <Error />,
    children: [
      {
        path: "",
        element: <Dashboard />,
      },
      {
        path: "accounts",
        element: <Home />,
      },
      {
        path: "accountdetail/:id",
        element: <AccountDetail />,
      },
      {
        path: "addaccount",
        element: <AddAccount />,
      },
      {
        path: "withdraw/:id",
        element: <Withdraw />,
      },
      {
        path: "deposit/:id",
        element: <Deposit />,
      },
    ],
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Register />,
  },
]);
