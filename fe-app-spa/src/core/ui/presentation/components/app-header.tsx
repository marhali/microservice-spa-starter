import { Link, NavLink } from 'react-router-dom';
import useAuthenticationContext from '@/core/authentication/presentation/use-authentication-context.ts';

function AppHeader() {
  const { username, iat, exp, roles } = useAuthenticationContext();

  return (
    <header>
      <nav className="bg-blue-900 px-4 py-2.5 text-white lg:px-6">
        <div className="mx-auto flex max-w-screen-xl flex-wrap items-center justify-between">
          <Link to="/" className="flex items-center">
            <span className="self-center whitespace-nowrap text-xl font-semibold">microservice-spa-starter</span>
          </Link>
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
        <div className="mx-auto flex max-w-screen-xl flex-wrap items-center justify-between text-xs">
          <span>Roles: {roles.join(', ')}</span>
          <span>
            Session: {new Date(iat).toLocaleTimeString()} - {new Date(exp).toLocaleTimeString()}
          </span>
        </div>
      </nav>
    </header>
  );
}

export default AppHeader;
