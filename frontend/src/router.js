import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Login from "./components/Login";
import Signup from "./components/Signup";
import ForgotPassword from "./components/resetpassword";
import Home from "./components/Home";
import Subscribe from "./components/Subscribe";



const router=createBrowserRouter([
    {path:'',element:<App/>},
    {path:'login',element:<Login/>},
    
    {path:'forgotpassword',element:<ForgotPassword/>},
    {path:'home',element:<Home/>},
    {path:'moviedetails',element:<MovieDetails/>},
    {path:'subscribe',element:<Subscribe/>},
    {path: 'signup' ,element:<Signup/>},

])

export default router;