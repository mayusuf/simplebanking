import { configureStore } from "@reduxjs/toolkit";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage/session";

import accountsReducer from "./accountsSlice";
import dashboardReducer from "./dashboardSlice";
import usersReducer from "./usersSlice";

const persistConfig = {
  key: "user", // Key for local storage
  storage, // Using local storage to persist
};

const persistAccountsConfig = {
  key: "account", // Key for local storage
  storage, // Using local storage to persist
};

const persistDashboard = {
  key: "dashboard", // Key for local storage
  storage, // Using local storage to persist
};


const persistedAccountReducer = persistReducer(persistAccountsConfig, accountsReducer);
const persistedDashboardReducer = persistReducer(persistDashboard, dashboardReducer);
const persistedReducer = persistReducer(persistConfig, usersReducer);

export const store = configureStore({
  reducer: {
    accounts: persistedAccountReducer,
    dashboardData: persistedDashboardReducer,
    users: persistedReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false, // Disable serializable check for redux-persist
    }),
});

export const persistor = persistStore(store);

// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>;
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;
