<<<<<<< HEAD
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import BusinessProfile from './pages/BusinessProfile';
import RegistrationForm from './components/ParentComponent';
import Login from './components/Login';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import AuditList from './pages/AuditList';
import AuthCallback from './components/AuthCallback';
import GetProducts from './pages/GetProducts';
import AddToCart from './pages/AddToCart';
import CustomerAddress from './pages/CustomerAddress';
import Payment from './pages/Payment';
=======
import logo from './logo.svg';
import './App.css';
>>>>>>> 37e21b9 (initial commit)

function App() {
  return (
    <div className="App">
<<<<<<< HEAD

      <BrowserRouter>
        <ToastContainer />
        <Routes>
          <Route path="/" element={<RegistrationForm />} />
          <Route path="/company" element={<RegistrationForm />} />
          <Route path="/security" element={<RegistrationForm />} />
          <Route path="/login" element={<Login />} />
          <Route path="/auth/callback" element={<AuthCallback />} />
   
          <Route path="/allProducts" element={<GetProducts />}>
            <Route path="addTocart" element={<AddToCart />} />
            <Route path="address" element={<CustomerAddress />} />
            <Route path="payment" element={<Payment />} />
          </Route>
        </Routes>
      </BrowserRouter>
=======
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
>>>>>>> 37e21b9 (initial commit)
    </div>
  );
}

export default App;
