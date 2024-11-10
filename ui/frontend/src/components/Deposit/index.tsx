import React, { useState, ChangeEvent, FormEvent, useEffect } from "react";
import Swal from "sweetalert2";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate, useParams } from "react-router-dom";
import { useAppSelector } from "../../redux/hooks";
import { depositMoney } from "../../apis/services/deposit.service";

interface DepositDataType {
  accountNumber: string;
  amount: number;
  description: string;
}

const Deposit = () => {
  const navigate = useNavigate();
  const { id: account_id } = useParams();
  const { accounts } = useAppSelector((state) => state.accounts);
  const account = accounts?.find((item) => item?.id === Number(account_id));

  const [depositData, setDepositData] = useState<DepositDataType>({
    accountNumber: "",
    amount: 0,
    description: "",
  });

  useEffect(() => {
    if (account_id) {
      setDepositData((prev: any) => ({
        ...prev,
        accountNumber: account?.accountNo ?? "",
        amount: account?.balance ?? 0,
      }));
    }
  }, [account_id, account]);

  const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setDepositData({ ...depositData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const result = await Swal.fire({
      title: "Are you sure?",
      text: "Do you want to proceed with the deposit?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, proceed!",
    });


    if (result.isConfirmed) {
      if (account && account_id) {
        const response = await depositMoney({
          amount: Number(depositData?.amount),
          description: depositData?.description,
          account_id: account_id
        });
        console.log(response.data);
        // dispatch(updateBalance({ ...account, balance: depositData?.amount }));

        toast.success("Deposit request submitted successfully!", {
          position: "bottom-center",
          onClose: () => {
            setDepositData({
              accountNumber: "",
              amount: 0,
              description: "",
            });
            navigate("/");
          },
        });
      }
    }

  };

  return (
      <div className="container mt-5">
        <h2>Deposit Form</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Account Number</label>
            <input
                type="text"
                className="form-control"
                name="accountNumber"
                value={depositData.accountNumber}
                onChange={handleChange}
                required
                disabled
            />
          </div>
          <div className="form-group mt-3">
            <label>Amount</label>
            <input
                type="number"
                className="form-control"
                name="amount"
                value={depositData.amount}
                onChange={handleChange}
                required
            />
          </div>
          <div className="form-group mt-3">
            <label>Note</label>
            <textarea
                className="form-control"
                name="description"
                value={depositData.description}
                onChange={handleChange}
                required
            ></textarea>
          </div>
          <button type="submit" className="btn btn-primary mt-4">
            Submit
          </button>
        </form>
        <ToastContainer />
      </div>
  );
};

export default Deposit;