import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import BusinessProfile from './pages/BusinessProfile';

function App() {
  return (
 
  <BrowserRouter>
    <Routes>
       <Route path='/business-profile' element={<BusinessProfile/>}></Route>
    </Routes>
  </BrowserRouter>

  );
}

export default App;
