import "./App.css";
import Navbar from "./components/Navbar";
import DarkMode from "./components/DarkMode";
import { useAppDispatch, useAppSelector } from "./redux/hooks";
import { getUser } from "./redux/usersSlice";
import React, { useEffect } from "react";
import {Navigate, Outlet} from "react-router-dom";
import {ToastContainer} from "react-toastify";

function App( ) {
  const dispatch = useAppDispatch();
  const { users } = useAppSelector((state) => state.users);
  useEffect(() => {
    dispatch(getUser());
  }, []);

  return (
    <>
      {users.token.trim() === "" ? (
        <Navigate to="/login" replace={true} />
      ) : (
        <>
          <DarkMode />
          <Navbar />
          <main className="container">
            <Outlet/>
          </main>

          <ToastContainer/>
        </>
      )}
    </>
  );
}

export default App;
