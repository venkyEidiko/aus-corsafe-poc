import { BrowserRouter,Route,Routes } from 'react-router-dom';
import './App.css';
import BusinessProfile from './pages/BusinessProfile';
import RegistrationForm from './components/ParentComponent';



import Login from './components/Login';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';

import GetProducts from './pages/GetProducts';
import AddToCart from './pages/AddToCart';
import CustomerAddress from './pages/CustomerAddress';
import Payment from './pages/Payment';


function App() {
  return (
    <div className="App">

  
  <BrowserRouter>
  <ToastContainer/>
      <Routes>
        <Route path="/" element={<RegistrationForm />} />
        <Route path="/login" element={<Login />} />
        <Route path="/business-profile" element={<BusinessProfile />} />
        
      
        <Route path="/allProducts" element={<GetProducts />}> 
          <Route path="addTocart" element={<AddToCart />} />     
          <Route path="address" element={<CustomerAddress />} />
          <Route path="payment" element={<Payment />} />
        </Route>
       
      </Routes>
    </BrowserRouter>
   

<BrowserRouter>
<ToastContainer />
     <Routes>
    <Route path="/" element={<RegistrationForm/>}></Route>
    <Route path="/login" element={<Login/>}></Route>
    <Route path="/company" element={<RegistrationForm/>}></Route>
    <Route path="/security" element={<RegistrationForm/>}/>
     <Route path='/business-profile' element={<BusinessProfile/>}></Route>

     </Routes>
     </BrowserRouter>



    </div>
  );
}

export default App;
