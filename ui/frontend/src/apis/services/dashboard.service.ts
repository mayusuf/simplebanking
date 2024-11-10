import {Account} from "../../types/Account";

import axios from 'axios';
import * as url_config from "../URLs";
import {Address} from "../../types/Address";


export const getDashboardData = () => {
    return axios.get(url_config.default.dashboard);
}

