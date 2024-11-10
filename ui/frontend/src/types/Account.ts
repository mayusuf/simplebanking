import { Address } from "./Address"

export interface Account{
    id:number,
    accountNo: string,
    name:string,
    email:string,
    accountType:string,
    address:Address,
    balance: number
}