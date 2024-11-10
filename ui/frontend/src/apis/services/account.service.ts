import {Account} from "../../types/Account";

import axios from 'axios';
import * as url_config from "../URLs";
import {Address} from "../../types/Address";

type AccountDTO={
    accountNo: string,
    name:string,
    email:string,
    accountType:string,
    address:Address,
    balance: number,
    street:string,
    city:string,
    state: string,
    zip: number
}
export const getAll = () => {
    // return http.get('/accounts');
    return axios.get(url_config.default.accounts);
}

export const getById = (id: number) => {
    return axios.get(url_config.default.accounts+'/' + id);
}

export const addAccount = (account: Account) => {
    const accountDto = {...account,...account.address};
    return axios.post(url_config.default.accounts, accountDto);
}