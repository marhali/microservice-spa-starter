type ErrorProperties = {
  error: unknown;
};

function Error(properties: ErrorProperties) {
  const { error } = properties;

  return (
    <div>
      <h1>Damn! Something went wrong...</h1>
      <p>{String(error)}</p>
    </div>
  );
}

export default Error;
