import {createSlice} from '@reduxjs/toolkit'
import type {PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "./store";
import {Account} from "../types/Account";
import {AccountDto} from "../types/AccountDto";
import {number} from "yup";

// Define a type for the slice state
export type DashboardDto={
    savingAccountCount: number,
    checkingAccountCount: number,
    totalDeposits: number,
    totalWithdraws: number,
}
interface DashboardState {
    dashboardData: DashboardDto
}

// Define the initial state using that type
const initialState: DashboardState = {
    dashboardData: {
        savingAccountCount: 0,
        checkingAccountCount: 0,
        totalDeposits: 0,
        totalWithdraws: 0
    } as DashboardDto
}

export const dashboardSlice = createSlice({
    name: 'dashboard',
    // `createSlice` will infer the state type from the `initialState` argument
    initialState,
    reducers: {
        loadDashboardData: (state, action: PayloadAction<DashboardDto>) => {
            state.dashboardData = {...action.payload};
        }
    },
})

export const {loadDashboardData} = dashboardSlice.actions

// Other code such as selectors can use the imported `RootState` type
export const dashboardData = (state: RootState) => state.dashboardData;

export default dashboardSlice.reducer;