import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();   // navigation hook

  const handleLogin = async (e) => {
    e.preventDefault();

    try {

      const response = await axios.post(
        "http://localhost:8080/api/v1/auth/login",
        {
          email: email,
          password: password
        }
      );

      const token = response.data.token;

      localStorage.setItem("token", token);

      console.log("JWT Token:", token);

      navigate("/dashboard");   // redirect after login

    } catch (error) {

      alert("Login failed");

      console.error(error);

    }
  };

  return (
    <div>

      <h2>Login Page</h2>

      <form onSubmit={handleLogin}>

        <div>
          <input
            type="email"
            placeholder="Enter Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <br />

        <div>
          <input
            type="password"
            placeholder="Enter Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <br />

        <button type="submit">Login</button>

      </form>

    </div>
  );
}

export default Login;