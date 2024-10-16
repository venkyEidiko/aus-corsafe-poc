
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import BusinessProfile from './pages/BusinessProfile';
import RegistrationForm from './components/ParentComponent';
import Login from './components/Login';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
function App() {
  return (
    <div className="App">

<BrowserRouter>
<ToastContainer />
     <Routes>
    <Route path="/" element={<RegistrationForm/>}></Route>
    <Route path="/login" element={<Login/>}></Route>
    <Route path="/company" element={<RegistrationForm/>}></Route>
    <Route path='/business-profile' element={<BusinessProfile/>}></Route>
     </Routes>
     </BrowserRouter>
      
    </div>

  );
}

export default App;
