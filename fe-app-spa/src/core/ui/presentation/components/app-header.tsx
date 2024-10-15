import { NavLink } from 'react-router-dom';

function AppHeader() {
  //const { username } = useAuthenticationContext();
  const username = 'myMockedUsername';

  return (
    <header>
      <nav className="bg-blue-900 px-4 py-2.5 text-white lg:px-6">
        <div className="mx-auto flex max-w-screen-xl flex-wrap items-center justify-between">
          <a href="/" className="flex items-center">
            <span className="self-center whitespace-nowrap text-xl font-semibold">BFF Demo</span>
          </a>
          <div className="flex items-center space-x-2">
            <NavLink to="/todo" className={({ isActive }) => (isActive ? 'underline' : '')}>
              ToDo's
            </NavLink>
          </div>
          <div className="flex items-center space-x-2">
            <span>[{username}]</span>
            <a href="/api/logout">Logout</a>
          </div>
        </div>
      </nav>
    </header>
  );
}

export default AppHeader;
