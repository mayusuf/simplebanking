import { useState, ChangeEvent, FormEvent, useEffect } from "react";
import Swal from "sweetalert2";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useParams } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import { updateBalance } from "../../redux/accountsSlice";
import {
  withdrawHistory,
  withdrawMoney,
} from "../../apis/services/withdraw.service";

interface WithdrawDataType {
  accountNumber: string;
  amount: number;
  description: string;
}

const Withdraw = () => {
  const { id: account_id } = useParams();
  const { accounts } = useAppSelector((state) => state.accounts);
  const account = accounts?.find((item) => item?.id === Number(account_id));
  const dispatch = useAppDispatch();

  const [withdrawData, setWithdrawData] = useState<WithdrawDataType>({
    accountNumber: "",
    amount: 0,
    description: "",
  });

  const [withdrawHistoryData, setWithdrawHistoryData] = useState([]);
  const [loading, setLoading] = useState(false);

  const loadHistory = async (account_id: string) => {
    setLoading(true);

    const response = await withdrawHistory(account_id);

    if (Array.isArray(response?.data)) {
      setWithdrawHistoryData(response?.data as any);
    }

    setLoading(false);
  };

  useEffect(() => {
    if (account_id) {
      loadHistory(account_id);
      setWithdrawData((prev: any) => ({
        ...prev,
        accountNumber: account?.accountNo ?? "",
        amount: account?.balance ?? 0,
      }));
    }
  }, [account_id, account]);

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setWithdrawData({ ...withdrawData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const result = await Swal.fire({
      title: "Are you sure?",
      text: "Do you want to proceed with the withdrawal?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, proceed!",
    });

    if (result.isConfirmed) {
      if (account && account_id) {
        try {
          await withdrawMoney({
            amount: Number(withdrawData?.amount),
            description: withdrawData?.description,
            account_id: account_id,
          });
          const newBalance = account.balance - withdrawData.amount;
          dispatch(updateBalance({ ...account, balance: newBalance }));

          toast.success("Withdrawal request submitted successfully!", {
            position: "bottom-center",
            onClose: () => {
              setWithdrawData((prev: any) => ({
                ...prev,
                description: "",
              }));
              loadHistory(account_id);
            },
          });
        } catch (error: any) {
          toast.error(error?.response?.data, {
            position: "bottom-center",
            onClose: () => {
              setWithdrawData((prev: any) => ({
                ...prev,
                description: "",
              }));
              loadHistory(account_id);
            },
          });
        }
      }
    }
  };

  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-6">
          <h3>Withdraw History</h3>
          {loading ? (
            <div className="text-center">
              <div className="spinner-border" role="status">
                <span className="visually-hidden">Loading...</span>
              </div>
            </div>
          ) : (
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Date</th>
                  <th>Amount</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                {withdrawHistoryData.length > 0 ? (
                  withdrawHistoryData.map((historyItem: any) => (
                    <tr key={historyItem.id}>
                      <td>{historyItem.id}</td>
                      <td>{historyItem.date}</td>
                      <td>{historyItem.amount}</td>
                      <td>{historyItem.description}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={5} className="text-center">
                      No history available
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          )}
        </div>

        <div className="col-md-6">
          <h2>Withdraw Form</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Account Number</label>
              <input
                type="text"
                className="form-control"
                name="accountNumber"
                value={withdrawData.accountNumber}
                readOnly
              />
            </div>
            <div className="form-group mt-3">
              <label>Amount</label>
              <input
                type="number"
                className="form-control"
                name="amount"
                value={withdrawData.amount}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group mt-3">
              <label>Note</label>
              <textarea
                className="form-control"
                name="description"
                value={withdrawData.description}
                onChange={handleChange}
              ></textarea>
            </div>
            <button type="submit" className="btn btn-primary mt-4">
              Submit
            </button>
          </form>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};

export default Withdraw;
