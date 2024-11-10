import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {useEffect} from "react";
import {loadCurrentAccount} from "../../redux/accountsSlice";
import {NavLink, useParams} from "react-router-dom";

export default function Index() {
    const {account} = useAppSelector(state => state.accounts);
    const dispatch = useAppDispatch();
    const { id} = useParams();
    useEffect(() => {
        dispatch(loadCurrentAccount(Number(id)));
    }, []);
    return <>
        {account ? <>
                <div className='card'>
                    <div className='card-body'>
                        <h5 className="card-title">
                            <NavLink to='/accounts'>Accounts</NavLink>
                            &nbsp; / Account detail
                        </h5>
                        <hr/>
                        <div className='row'>
                            <h4 className='col-3 '>Account No: </h4>
                            <h4 className='col-9 '> {account.accountNo}</h4>
                        </div>
                        <div className='row'>
                            <h4 className='col-3 '>Name: </h4>
                            <h4 className='col-9 '> {account.name}</h4>
                        </div>
                        <div className='row'>
                            <h4 className='col-3 '>Email: </h4>
                            <h4 className='col-9 '> {account.email}</h4>
                        </div>
                        <div className='row'>
                            <h4 className='col-3 '>Account Type: </h4>
                            <h4 className='col-9 '> {account.accountType}</h4>
                        </div>
                        <div className='row'>
                            <h4 className='col-3 '>Balance: </h4>
                            <h4 className='col-9 '> {account.balance}</h4>
                        </div>
                        <div className='row'>
                            <h4 className='col-3 '>Address: </h4>
                            <h4 className='col-9 '> {account.street}, {account.city}, {account.state} - {account.zip}</h4>
                        </div>

                    </div>

                </div>
            </> :
            <> Something went wrong</>}
    </>
}