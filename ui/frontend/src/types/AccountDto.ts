import {Address} from "./Address";

export interface AccountDto{
    id:number,
    accountNo: string,
    name:string,
    email:string,
    accountType:string,
    balance: number,
    street:string,
    city:string,
    state: string,
    zip: number
}