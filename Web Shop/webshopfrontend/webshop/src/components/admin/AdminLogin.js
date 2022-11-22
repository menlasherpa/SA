import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AdminHeader } from "./AdminHeader";
export const AdminLogin = (props) => {
  const cleanuser = { username: "", password: "" };
  const [user, setUser] = useState(cleanuser);
  const admin = useSelector((state) => state.admin);
  const [loginError, setLoginError] = useState({});

  const handleFieldChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const submitUser = (e) => {
    const errorMsg = {};
    e.preventDefault();
    const isValid = isValidLogin();
    if (isValid) {
      props.history.push("/dashboard", { user: user });
    } else {
      errorMsg.wrongmsg =
        "Wrong!! You can only login with the username/password combination that corresponse with created accounts(s) that are saved in the redux store";
    }
    setLoginError(errorMsg);
    setUser(cleanuser);
  };

  const isValidLogin = () => {
    let isValidLogin = false;

    if (user.username === admin.username && user.password === admin.password) {
      isValidLogin = true;
    }

    return isValidLogin;
  };

  const createUser = () => {
    props.history.push("/");
  };

  return (
    <div>
        <AdminHeader/>
      <div>
        <div id="error">
          {Object.keys(loginError).map((key) => {
            return <div style={{ color: "red" }}>{loginError[key]}</div>;
          })}
        </div>

        <h1 >Admin Login</h1>
        <form>
          <div>
            Username
            <input
              type="text"
              name="username"
              value={user.username}
              placeholder="username"
              onChange={handleFieldChange}
            />
          </div>
          <div>
            Password
            <input
              type="password"
              name="password"
              value={user.password}
              placeholder="password"
              onChange={handleFieldChange}
            />
          </div>

          <button id="submitUser" onClick={submitUser}>
            Login
          </button>
          
        </form>
      </div>
    </div>
  );
};
