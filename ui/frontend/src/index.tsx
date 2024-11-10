import axios from "axios";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import { Provider } from "react-redux";
import { persistor, store } from "./redux/store";
import { router } from "./routing/router";
import { setupAxios } from "./apis/Handler";
import { PersistGate } from "redux-persist/integration/react";

setupAxios(axios);

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <div className="container">
    <div className="row">
      <div className="col-12">
        <Provider store={store}>
          <PersistGate loading={null} persistor={persistor}>
            <RouterProvider router={router} />
          </PersistGate>
        </Provider>
      </div>
    </div>
  </div>
);
