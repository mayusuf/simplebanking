import axios from "axios";
import { Withdraw } from "../../types/Withdraw";
import * as url_config from "../URLs";

export const withdrawMoney = (withdraw: Withdraw) => {
    return axios.post(url_config.default.withdraw, withdraw);
}

export const withdrawHistory = (id: string) => {
    return axios.get(url_config.default.withdrawHistory(id));
}