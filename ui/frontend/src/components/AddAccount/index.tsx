import React, {ChangeEvent, FormEvent, useState} from "react";
import Swal from "sweetalert2";
import {toast, ToastContainer} from "react-toastify";
import {Account} from "../../types/Account";
import {Address} from "../../types/Address";
import {NavLink, useNavigate} from "react-router-dom";
import {addAccount as httpAddAccount} from "../../apis/services/account.service";
import {addAccount, loadAccounts} from "../../redux/accountsSlice";
import {useAppDispatch} from "../../redux/hooks";
import {AccountDto} from "../../types/AccountDto";


export default function AddAccount() {
    const dispatch = useAppDispatch();
    const navigate = useNavigate();
    const emptyAccount: Account = {
        id: 0,
        accountNo: '',
        name: '',
        email: '',
        accountType: 'Savings',
        address: {street: '', city: '', state: '', zip: 0},
        balance: 0
    } as Account;
    const [account, setAccount] = useState<Account>(emptyAccount);
    const handleChange = (
        e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        setAccount({...account, [e.target.name]: e.target.value});
    };
    const handleAddressChange = (
        e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        setAccount({...account,address:{...account.address,[e.target.name]: e.target.value} });
    };

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        Swal.fire({
            title: "Are you sure?",
            text: "Please confirm creation of new Account?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, proceed!",
        }).then((result) => {
            if (result.isConfirmed) {
                if (account) {
                    (async()=> {
                            await httpAddAccount(account)
                                .then(response => {
                                    dispatch(addAccount(response.data as AccountDto));
                                }).catch(error => {
                                    toast.error("Failed to add account!", {
                                        position: "bottom-center",
                                        onClose: () => {

                                        },
                                    });
                                });
                        }
                )();
                }
                toast.success("Account added successfully!", {
                    position: "bottom-center",
                    onClose: () => {
                        setAccount(emptyAccount);
                        navigate("/");
                    },
                });
            }
        });
    };
    return (
        <div className='card'>
            <div className='card-body'>
                <h5 className="card-title">
                    <NavLink to='/accounts'>Accounts</NavLink>
                    &nbsp; / Add new account</h5>
                <div className='border my-3'>
                    <form className="p-3" onSubmit={handleSubmit}>
                        <div className="row mb-3">
                            <label className="col-sm-2 col-form-label">Account #</label>
                            <div className="col-sm-10">
                                <input type="text"
                                       className="form-control"
                                       name="accountNo"
                                       value={account.accountNo}
                                       onChange={handleChange}
                                       required/>
                            </div>
                        </div>
                        <div className="row mb-3">
                            <label className="col-sm-2 col-form-label">Name</label>
                            <div className="col-sm-10">
                                <input type="text"
                                       className="form-control"
                                       name="name"
                                       value={account.name}
                                       onChange={handleChange}
                                       required
                                />
                            </div>
                        </div>
                        <div className="row mb-3">
                            <label className="col-sm-2 col-form-label">Email</label>
                            <div className="col-sm-10">
                                <input type="email"
                                       className="form-control"
                                       name="email"
                                       value={account.email}
                                       onChange={handleChange}
                                       required
                                />
                            </div>
                        </div>
                        <fieldset className="row mb-3">
                            <legend className="col-form-label col-sm-2 pt-0">Account Type</legend>
                            <div className="col-sm-10">
                                <div className="form-check">
                                    <input className="form-check-input" type="radio" name="accountType"
                                           id="checkingRadio"
                                           value='Checking' checked={account.accountType === 'Checking'}
                                           onChange={handleChange}/>
                                    <label className="form-check-label" htmlFor="checkingRadio">
                                        Checking
                                    </label>
                                </div>
                                <div className="form-check">
                                    <input className="form-check-input" type="radio" name="accountType"
                                           id="savingsRadio"
                                           value='Savings' checked={account.accountType === 'Savings'}
                                           onChange={handleChange}/>
                                    <label className="form-check-label" htmlFor="savingsRadio">
                                        Savings
                                    </label>
                                </div>
                            </div>
                        </fieldset>

                        <label className="col-sm-12 col-form-label">Address</label>
                        <hr/>
                        <div className="row mb-3">
                            <label className="col-sm-2 col-form-label">Street</label>
                            <div className="col-sm-10">
                                <input type="text"
                                       className="form-control"
                                       name="street"
                                       value={account.address.street}
                                       onChange={handleAddressChange}
                                       required
                                />
                            </div>
                        </div>
                        <div className="row mb-3">
                            <label className="col-sm-2 col-form-label">City</label>
                            <div className="col-sm-10">
                                <input type="text"
                                       className="form-control"
                                       name="city"
                                       value={account.address.city}
                                       onChange={handleAddressChange}
                                       required/>
                            </div>
                        </div>
                        <div className="row mb-3">
                            <label className="col-sm-2 col-form-label">State</label>
                            <div className="col-sm-10">
                                <input type="text"
                                       className="form-control"
                                       name="state"
                                       value={account.address.state}
                                       onChange={handleAddressChange}
                                       required
                                />
                            </div>
                        </div>
                        <div className="row mb-3">
                            <label className="col-sm-2 col-form-label">Zip Code</label>
                            <div className="col-sm-10">
                                <input type="number"
                                       className="form-control"
                                       name="zip"
                                       value={account.address.zip}
                                       onChange={handleAddressChange}
                                       required
                                />
                            </div>
                        </div>
                        <hr/>
                        <button type="submit" className="btn btn-primary">Save Account</button>
                    </form>
                </div>
            </div>
        </div>


    )
}