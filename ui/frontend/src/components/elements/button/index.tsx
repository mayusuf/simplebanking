export const Button = (props: any) => {
  return (
    <button
      className="btn btn-primary"
      type={props.type}
    >
      {props.children}
    </button>
  );
};
