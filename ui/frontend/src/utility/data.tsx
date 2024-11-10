export const request_signin = {
  username: "",
  password: "",
};

export const data_signin = [
  {
    title: "Password",
    data: "password",
    type: "text",
    initialValue: "",
    validationType: "string",
    validations: [
      {
        type: "required",
        params: ["this field is required"],
      },
      {
        type: "min",
        params: [5, "current min: 5 characters"],
      },
    ],
  },
  {
    title: "Username",
    data: "username",
    type: "text",
    initialValue: "",
    validationType: "string",
    validations: [
      {
        type: "required",
        params: ["this field is required"],
      },
      {
        type: "min",
        params: [5, "current min: 5 characters"],
      },
    ],
  },
];


export const request_register = {
  username: "",
  firstName: "",
  lastName: "",
  email: "",
  mobile: "",
};

export const data_register = [
  {
    title: "Username",
    data: "username",
    type: "text",
    initialValue: "",
    validationType: "string",
    validations: [
      {
        type: "required",
        params: ["this field is required"],
      },
      {
        type: "min",
        params: [5, "current min: 5 characters"],
      },
    ],
  },
  {
    title: "FirstName",
    data: "firstName",
    type: "text",
    initialValue: "",
    validationType: "string",
    validations: [
      {
        type: "required",
        params: ["this field is required"],
      },
      {
        type: "min",
        params: [5, "current min: 5 characters"],
      },
    ],
  },
  {
    title: "LastName",
    data: "lastName",
    type: "text",
    initialValue: "",
    validationType: "string",
    validations: [
      {
        type: "required",
        params: ["this field is required"],
      },
      {
        type: "min",
        params: [5, "current min: 5 characters"],
      },
    ],
  },
  {
    title: "Email",
    data: "email",
    type: "text",
    initialValue: "",
    validationType: "string",
    validations: [
      {
        type: "required",
        params: ["this field is required"],
      },
      {
        type: "min",
        params: [5, "current min: 5 characters"],
      },
    ],
  },
  {
    title: "Mobile",
    data: "mobile",
    type: "text",
    initialValue: "",
    validationType: "string",
    validations: [
      {
        type: "required",
        params: ["this field is required"],
      },
      {
        type: "min",
        params: [5, "current min: 5 characters"],
      },
    ],
  },
];
