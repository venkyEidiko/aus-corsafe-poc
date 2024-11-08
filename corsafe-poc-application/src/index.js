import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 639f34e529398f641dd4984732e49c37df3cc856
import store from './store/store.js';
import { Provider } from 'react-redux';




const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(


  <React.StrictMode>
    <Provider store={store}>
    <App/>
  
    </Provider>
  </React.StrictMode>

);


<<<<<<< HEAD
=======

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
>>>>>>> 37e21b9 (initial commit)
=======
>>>>>>> 639f34e529398f641dd4984732e49c37df3cc856
reportWebVitals();
