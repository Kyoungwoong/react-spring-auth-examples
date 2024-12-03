import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import { RoutePath } from '../interface/RoutePath';
import HomePage from './home/HomePage';
import CookieLogin from './cookie/CookieLogin';
import SessionLogin from './session/SessionLogin';
import SecurityLogin from './security/SecurityLogin';
import OAuth2Login from './oauth2/OAuth2Login';

const AppRoutes = () => {
    return (
      <Router>
          <Routes>
              <Route path={RoutePath.HOMEPAGE} element={<HomePage />} />
              <Route path={RoutePath.COOKIE} element={<CookieLogin />} />
              <Route path={RoutePath.SESSION} element={<SessionLogin />} />
              <Route path={RoutePath.SECURITY} element={<SecurityLogin />} />
              <Route path={RoutePath.OAUTH2} element={<OAuth2Login />} />
          </Routes>
      </Router>
    )
  }
  
  export default AppRoutes