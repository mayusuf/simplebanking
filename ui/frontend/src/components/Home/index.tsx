import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {useEffect} from "react";
import {Link} from "react-router-dom";
import {getAll, getById, addAccount} from './../../apis/services/account.service'
import {loadAccounts} from "../../redux/accountsSlice";
import {Account} from "../../types/Account";
import {log} from "node:util";
import {AccountDto} from "../../types/AccountDto";
import {toast} from "react-toastify";

export default function Home() {
    const {accounts} = useAppSelector(state => state.accounts);
    const dispatch = useAppDispatch();

    const fetchAccounts = async () => {
        await getAll()
            .then(response => {
                dispatch(loadAccounts(response.data as AccountDto[]));
            }).catch(error => {
                    toast.error("Failed to fetch accounts!", {
                        position: "bottom-center",
                        onClose: () => {

                        },
                    });
                }
            );
    }
    useEffect(() => {
        (async () => {
            await fetchAccounts();
        })();
    }, []);

    return (<>

        <div className='card'>
            <div className='card-body'>
                <h5 className="card-title">Accounts</h5>
                {accounts.length === 0 ?
                    /*<div className="alert alert-primary" role="alert">
                        No account available!
                    </div>*/
                    <div>No account available</div>
                    :
                    <div>
                        <div className="d-flex justify-content-between">
                            <div>
                                Showing {accounts.length} accounts from {accounts.length}
                            </div>
                            <div>
                                <Link className='p-2' to={'/addAccount'}>
                                    Add Account
                                </Link>
                            </div>
                        </div>

                        <table className="table table-hover border my-3">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Account NO</th>
                                <th scope="col">Name</th>
                                <th scope="col">Address</th>
                                <th scope="col">Email</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                accounts.map((account) => {
                                    return <tr key={account.id}>
                                        <th scope="row">{account.id}</th>
                                        <td>
                                            <Link className='p-2' to={'/accountdetail/' + account.id}>
                                                {account.accountNo}
                                            </Link></td>
                                        <td>{account.name}</td>
                                        <td>
                                            <div>
                                                {account.street}, {account.city}, {account.state} - {account.zip}
                                            </div>
                                        </td>
                                        <td>{account.email}</td>
                                        <td>{account.balance}</td>
                                        <td>
                                            <Link className='p-2' to={'/deposit/' + account.id}>
                                                Deposit
                                            </Link>
                                            <Link className='p-2' to={'/withdraw/' + account.id}>
                                                Withdraw
                                            </Link>
                                        </td>
                                    </tr>
                                })
                            }
                            </tbody>
                        </table>
                    </div>
                }
            </div>
        </div>
    </>);
}