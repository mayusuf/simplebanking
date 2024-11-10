const url = "http://localhost:9002/api/v1";

const global = {
  signing: url + "/sign-in",
  registration: url + "/registration",
  accounts: url + "/accounts",
  dashboard: url + "/dashboard",
  withdraw: url + "/withdraw",
  withdrawHistory: (id: string) => `${url}/withdraw/${id}`,
  deposit: url + "/deposit"
};

export default global;
