
import './App.css';

import { BrowserRouter,Route,Routes } from 'react-router-dom';
import RegistrationForm from './components/ParentComponent';

function App() {
  return (
    <div className="App">

<BrowserRouter>
     <Routes>
    <Route path="/" element={<RegistrationForm/>}></Route>
    <Route path="/company" element={<RegistrationForm/>}></Route>
     </Routes>
     </BrowserRouter>
      
    </div>
  );
}

export default App;
