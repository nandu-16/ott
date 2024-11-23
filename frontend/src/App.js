import React from "react";
import Login from "./components/Login";
import Landingpage from "./components/Landingpage";
import Homepage from "./components/Homepage";
import Moviedetails from "./components/Moviedetails";
import SubscriptionPlans from "./components/SubscriptionPlans";
import ManageAccount from "./components/ManageAccount";
import 'bootstrap/dist/css/bootstrap.min.css';
import NewPassword from "./components/NewPassword";
import { BrowserRouter as Router, Route, } from "react-router-dom";
import { Routes } from "react-router-dom";
import  SubscriptionManagement from "./components/SubscriptionManagement";
import ForgetPassword from "./components/ForgetPassword";
import ChangePassword from "./components/ChangePassword";
import Signup from "./components/Signup";
import WatchHistory from "./components/WatchHistory";
import axios from "axios";
import CommonNavbar from "./components/CommonNavbar";
import WatchLater from "./components/WatchLater";
import MovieCard from "./components/MovieCard";
//import MovieItem from "./components/MovieItem";

function App() {

  return (
   
    
      <Router>
      <Routes>
      {/* <Route path="/CommonNavbar" element={<CommonNavbar/>}/> */}
        <Route path="NewPassword" element={<NewPassword/>}/>
        <Route path="NewPassword" element={<ChangePassword/>}/>
        <Route path="ForgetPassword" element={<ForgetPassword />}/>
        <Route path="/" element={<Landingpage />} />
        <Route path="/Homepage" element={<Homepage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/subscription-plans" element={<SubscriptionPlans />} />  {/* Add other routes here */}
        <Route path="/manage-account" element={<ManageAccount />} />
        <Route path="/subscription-management" element={<SubscriptionManagement />} />
        <Route path="/SIGNUP" element={<Signup />} />
        <Route path="/login" element={<Login />} />        {/* Login page as default */}
        <Route path="/home" element={<Homepage />} /> {/* Home page after login */}
        <Route path="/watch-history" element={<WatchHistory />} />
        <Route path="/watch-later" element={<WatchLater />} />
        <Route path="/MovieCard" element={<MovieCard />} />
        <Route path="/Moviedetails/:id" element={<Moviedetails />} /> {/* Use parameter */}
        <Route path="/MovieCard" element={<MovieCard />} />
        {/* <Route path="/MovieItem" element={<MovieItem />} /> */}
        
      </Routes>

      </Router>
    
     
     
   
  );
}

export default App;
