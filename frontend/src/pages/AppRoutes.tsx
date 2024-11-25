import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import { RoutePath } from '../interface/RoutePath';
import HomePage from './home/HomePage';
import CookieLogin from './cookie/CookieLogin';

const AppRoutes = () => {
    return (
      <Router>
          <Routes>
              <Route path={RoutePath.HOMEPAGE} element={<HomePage />} />
              <Route path={RoutePath.COOKIE} element={<CookieLogin />} />
          </Routes>
      </Router>
    )
  }
  
  export default AppRoutes