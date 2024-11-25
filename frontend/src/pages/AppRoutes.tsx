import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import { RoutePath } from '../interface/RoutePath';
import HomePage from './home/HomePage';
import Cookie from './cookie/Cookie';

const AppRoutes = () => {
    return (
      <Router>
          <Routes>
              <Route path={RoutePath.HOMEPAGE} element={<HomePage />} />
              <Route path={RoutePath.COOKIE} element={<Cookie />} />
          </Routes>
      </Router>
    )
  }
  
  export default AppRoutes