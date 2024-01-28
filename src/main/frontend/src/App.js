import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Main from "./pages/Main/Main";
import NewForm from "./pages/Main/NewForm/NewForm";
import SignUp from "./pages/UserData/Sign/SignUp";
import SignIn from "./pages/UserData/Sign/SignIn";
import MainLayout from "./components/Layout/MainLayout";
import MyPageLayout from "./components/Layout/MyPageLayout";
import MyPage from "./pages/UserData/MyPage/MyPage";
import ResumeManage from "./pages/UserData/Resume/ResumeManage";
import Resume from "./pages/UserData/Resume/Resume";
import SelectResume from "./pages/UserData/Resume/SelectResume";
import ResumeList from "./pages/UserData/Resume/ResumeList";
import InterviewList from "./pages/Main/Interview/InterviewList";
import { RecoilRoot } from "recoil";
import ExInterviewList from "./pages/Main/Interview/ExInterviewList";
import SignOut from "./pages/UserData/Sign/SignOut";

const App = () => {
  return (
    <Router>
      <RecoilRoot>
        <Routes>
          <Route element={<MainLayout />}>
            <Route path="/" element={<Main />} />
            <Route path="selectResume" element={<SelectResume />} />
            <Route path="/newForm" element={<NewForm />} />
            <Route path="/signIn" element={<SignIn />} />
            <Route path="/signOut" element={<SignOut />} />
            <Route path="/signUp" element={<SignUp />} />
            <Route path="/resumeList" element={<ResumeList />} />
            <Route path="/interviewList" element={<InterviewList />} />
            <Route path="/exInterviewList" element={<ExInterviewList />} />
            <Route element={<MyPageLayout />}>
              <Route path="/myPage" element={<MyPage />} />
              <Route path="/resumeManage" element={<ResumeManage />} />
            </Route>
            <Route path="/resume" element={<Resume />} />
          </Route>
        </Routes>
      </RecoilRoot>
    </Router>
  );
};

export default App;
