export const Input = (props: any) => {
  return (
    <div className="row mb-2 from-group">
      <label
        htmlFor={props.id}
        className="block text-sm font-semibold text-gray-800 col-sm-2 col-form-label"
      >
        {props.label}
      </label>
      <div className="col-sm-10">
        <input
          id={props.id}
          type={props.type}
          value={props.value}
          onChange={props.onChange}
          className="form-control"
        />
      </div>
      {props.error ? (
        <label className="text-red-700">{props.helperText}</label>
      ) : (
        <></>
      )}
    </div>
  );
};
