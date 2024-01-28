import { Outlet } from "react-router-dom";
import MyPageNavigation from "../Navigation/MyPageNavigation";

const MyPageLayout = () => {
  return (
    <>
      <Outlet />
    </>
  );
};

export default MyPageLayout;
