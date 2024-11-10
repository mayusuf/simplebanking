import { createSlice } from "@reduxjs/toolkit";
import type { PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "./store";
import {
  getAuthentication,
  removeAuthentication,
  setAuthentication,
} from "../apis/Handler";
import { AuthenticationModel } from "../types/login";

export type User = {
  id: number;
  name: string;
  token: string;
};

// Define a type for the slice state
interface UsersState {
  users: User;
}

// Define the initial state using that type
const initialState: UsersState = {
  users: { id: 7, name: "Shaon", token: "" },
};

export const usersSlice = createSlice({
  name: "users",
  // `createSlice` will infer the state type from the `initialState` argument
  initialState,
  reducers: {
    addUser: (state, action: PayloadAction<User>) => {
    
      state.users = { ...state.users, ...action.payload };

      let auth: AuthenticationModel = {
        token: action.payload.token,
        name: action.payload.name,
      };
      setAuthentication(auth);
    },
    removeUser: (state, action: PayloadAction<User>) => {
      state.users = { ...state.users, ...action.payload };
      removeAuthentication();
    },
    getUser: (state) => {
      const auth: AuthenticationModel | undefined = getAuthentication();
      if (auth) {
        state.users = { ...state.users, ...auth };
      } else {
        state.users = { ...state.users };
      }
    },
  },
});

export const { addUser, removeUser, getUser } = usersSlice.actions;

// Other code such as selectors can use the imported `RootState` type
export const users = (state: RootState) => state.users;

export default usersSlice.reducer;
