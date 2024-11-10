import {createSlice} from '@reduxjs/toolkit'
import type {PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "./store";
import {Account} from "../types/Account";
import {AccountDto} from "../types/AccountDto";
import {number} from "yup";

// Define a type for the slice state
interface AccountsState {
    accounts: AccountDto[],
    account: AccountDto | undefined
}

// Define the initial state using that type
const initialState: AccountsState = {
    accounts: [],
    account:undefined
}

export const accountsSlice = createSlice({
    name: 'accounts',
    // `createSlice` will infer the state type from the `initialState` argument
    initialState,
    reducers: {
        loadAccounts: (state, action: PayloadAction<AccountDto[]>) => {
            state.accounts = [...action.payload];
        },
        loadCurrentAccount: (state, action: PayloadAction<number>) => {
            state.account = state.accounts.find(a => a.id === action.payload);
        },

        addAccount: (state, action: PayloadAction<AccountDto>) => {
            state.accounts = [...state.accounts, action.payload];
        },
        updateAccount: (state, action: PayloadAction<AccountDto>) => {
            state.accounts = state.accounts.map(a => {
                if (a.id === action.payload.id) {
                    return ({...action.payload, id: a.id});
                } else return a;
            });
        },
        updateBalance: (state, action: PayloadAction<Account | AccountDto>) => {
            state.accounts = state.accounts.map(a => {
                if (a.id === action.payload.id) {
                    return ({...a, balance: action.payload.balance});
                } else return a;
            });
        },
        removeAccount: (state, action: PayloadAction<Account | AccountDto>) => {
            state.accounts = state.accounts.filter(a => {
                return a.id !== action.payload.id
            });
        }
    },
})

export const {loadAccounts,loadCurrentAccount, addAccount, updateAccount, updateBalance, removeAccount} = accountsSlice.actions

// Other code such as selectors can use the imported `RootState` type
export const accounts = (state: RootState) => state.accounts

export default accountsSlice.reducer