import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import Index from "./components/Index";
import AuthProvider from "./hooks/AuthProvider";
import PrivateRoute from "./router/Route";
import {Toaster} from "react-hot-toast";
import Header from "./components/Header";
import Translate from "./components/Translate";

function App() {
  return (
      <div className="App">
        <Router>
          <AuthProvider>
            <Header/>
            <Routes>
              <Route path="/signIn" element={<SignIn/>}/>
              <Route path="/signUp" element={<SignUp/>}/>
              <Route element={<PrivateRoute/>}>
                <Route path="/" element={<Index/>}/>
                <Route path="/translate" element={<Translate/>}/>
              </Route>
            </Routes>
          </AuthProvider>
        </Router>
        <Toaster position="top-right"/>
      </div>
  );
}

export default App;