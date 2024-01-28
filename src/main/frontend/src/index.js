import React from "react";
import ReactDOM from "react-dom/client";

import { BrowserRouter } from "react-router-dom";

import App from "./App";
import "./global.css";

import store from './Store';
import { Provider } from 'react-redux';
import { CookiesProvider } from 'react-cookie';

import "bootstrap/dist/css/bootstrap.css";

const root = ReactDOM.createRoot(document.getElementById("root"));
// root.render(
//   <React.StrictMode>
//     <BrowserRouter>
//       <App />
//     </BrowserRouter>
//   </React.StrictMode>
// );
root.render(
  <CookiesProvider>
      <Provider store={store}>
          <App />
      </Provider>
  </CookiesProvider>,
);