import {NavLink} from "react-router-dom";
import {toast} from "react-toastify";
import {useEffect} from "react";
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {DashboardDto, loadDashboardData} from "../../redux/dashboardSlice";
import {getDashboardData} from "../../apis/services/dashboard.service";

export default function Dashboard() {
    const {dashboardData} = useAppSelector(state => state.dashboardData);
    const dispatch = useAppDispatch();

    const fetchDashboardData = async () => {
        await getDashboardData()
            .then(response => {
                dispatch(loadDashboardData(response.data as DashboardDto));
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
            await fetchDashboardData();
        })();
    }, []);

    return <div className='card'>
        <div className='card-body'>
            <h5 className="card-title">
                Dashboard
            </h5>
            <div className="px-4  text-center border-bottom">
                <h1 className="display-4 fw-bold text-body-emphasis">Simple Banking</h1>
                <div className="col-lg-6 mx-auto">
                    <p className="lead mb-4">This is a simple banking application built with Java Spring boot and React
                        under the project work on Web Application Architecture (WAA) course.

                        Here we are going to demonstrate the topics that we learned during this course through this project.
                        <br/>

                        Credit goes to our beloved professor - Mrs. Rujuan Xing and the team members
                        Nur A Shawal, Irfan, Hafiz and Yusuf.
                    </p>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center mb-5">
                       <NavLink to='/accounts'> Manage accounts</NavLink>
                    </div>
                </div>
            </div>
            <div className='row '>
                <div className='col-6 text-bg-primary'>
                    <figure className="figure ">
                        <h1>{dashboardData.savingAccountCount}</h1>
                        <figcaption className="figure-caption text-end">Number of Saving Account.</figcaption>
                    </figure>
                </div>
                <div className='col-6 d-flex text-bg-info'>
                    <figure className="figure ">
                        <h1>{dashboardData.checkingAccountCount}</h1>
                        <figcaption className="figure-caption text-end">Number of Checking accounts.</figcaption>
                    </figure>
                </div>
                <div className='col-6 text-bg-success'>
                    <figure className="figure">
                        <h1>{dashboardData.totalDeposits}</h1>
                        <figcaption className="figure-caption text-end">Total deposit amount.</figcaption>
                    </figure>
                </div>
                <div className='col-6 text-bg-warning'>
                    <figure className="figure">
                        <h1>{dashboardData.totalWithdraws}</h1>
                        <figcaption className="figure-caption text-end">Total withdraw amount.</figcaption>
                    </figure>
                </div>
            </div>
        </div>
    </div>
}