
import './App.css';
import BusinessProfile from './pages/BusinessProfile';

import { BrowserRouter,Route,Routes } from 'react-router-dom';
import RegistrationForm from './components/ParentComponent';

function App() {
  return (
    <div className="App">
  <BrowserRouter>
     <Routes>
    <Route path="/" element={<RegistrationForm/>}></Route>
    <Route path="/company" element={<RegistrationForm/>}></Route>
    <Route path='/business-profile' element={<BusinessProfile/>}></Route>
     </Routes>
     </BrowserRouter>
      
    </div>

  );
}

export default App;
