import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import { RoutePath } from '../interface/RoutePath';
import HomePage from './home/HomePage';
import CookieLogin from './cookie/CookieLogin';
import SessionLogin from './session/SessionLogin';

const AppRoutes = () => {
    return (
      <Router>
          <Routes>
              <Route path={RoutePath.HOMEPAGE} element={<HomePage />} />
              <Route path={RoutePath.COOKIE} element={<CookieLogin />} />
              <Route path={RoutePath.SESSION} element={<SessionLogin />} />
          </Routes>
      </Router>
    )
  }
  
  export default AppRoutes