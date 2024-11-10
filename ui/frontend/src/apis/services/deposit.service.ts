import axios from "axios";
import { Deposit } from "../../types/Deposit";
import * as url_config from "../URLs";

export const depositMoney = (deposit: Deposit) => {
    return axios.post(url_config.default.deposit, deposit);
}