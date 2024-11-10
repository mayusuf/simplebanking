import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import {NavLink, useNavigate} from "react-router-dom";
import { removeUser } from "../../redux/usersSlice";

export default function Navbar() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const { users } = useAppSelector((state) => state.users);

  const handleLogout = (event: any) => {
    dispatch(removeUser({ ...users, token: "" }));
  };

  return (
    <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <div className="container-fluid">

        <NavLink className="navbar-brand"  to={'/'}>Simple Banking</NavLink>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarCollapse">
          <ul className="navbar-nav me-auto mb-2 mb-md-0">
            <li className="nav-item">
              <NavLink className="nav-link active" to="/">Home</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link active" to="/accounts">Accounts</NavLink>
            </li>
          </ul>
          <form className="d-flex" >
            <label className="me-4 my-2 text-info text-center">
              Welcome <strong className='capital'>{users.name}</strong>
            </label>
            <button
              className="btn btn-outline-danger"
              onClick={handleLogout}
            >
              Logout
            </button>
          </form>
        </div>
      </div>
    </nav>
  );
}
