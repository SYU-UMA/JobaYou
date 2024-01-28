import React, { useEffect } from "react";

import { Input, Button } from "reactstrap";

import styles from "./ResumeManage.module.scss";

import { useNavigate,Navigate } from "react-router-dom";

import axios from "axios";
import AxiosInstance from "../../../api/AxiosInstance";
import Resume from "../Resume/Resume";
import StepDot from "../../../components/StepDot/StepDot";
import { useRecoilState } from "recoil";
import { isLoginState } from "../../../recoil/RecoilState";
import GNB from "../../../components/GNB/GNB";

const ResumeManage = () => {
  const navigate = useNavigate();
  
  const movePage = (url) => {
    navigate(url)
  };
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);

  /* 모달 페이지 컨트롤 */
  const [modalIsOpen, setModalIsOpen] = React.useState(false);

  //빈 이력서 생성
  const addButton = () => {
    AxiosInstance
		.post('/resume/add', {
			"userId" : "",
			"type" : "",
			"resumeName" : "무제",
			"introJob" : "",
 			"introContents" : "",
  		"core1" : "",
  		"core2" : "",
   		"core3" : "",
  		"core4" : "",
 			"core5" : "",
		}).then(() => {
      getResumeList();
      alert("완료!")
		})
  };
  //이력서 목록
  const [resumeList, setResumeList] = React.useState([]);
  //필요한만큼 자른 resumeList를 담는 state
  const [slicedResumeList, setSlicedResumeList] = React.useState([]);
  //page 관리
  const [currentPage, setCurrentPage] = React.useState(1);
  const maxRenderCount = 5;
  const maxPage =  Math.ceil(resumeList.length / maxRenderCount);
  const prevDisabled = (currentPage == 1);
    const nextDisabled = (currentPage == maxPage);

  const prevButton = () => {
    setCurrentPage(currentPage-1);
  }
  const nextButton = () => {
    setCurrentPage(currentPage+1);
  }
  const [isView, setIsView] = React.useState(false);

  const [isViewInterviewList, setIsViewInterviewList] = React.useState(false);

  //Resume.jsx에 넘겨줄 불러온 resumeNum/ careerNums/ skillNums/ myCareerNum
  const [resumeData, setResumeData] = React.useState({
    resumeNum: '',
    careerNums: [],
    skillNums: [],
    myCareerNum: '',
  });

  //사용자가 가진 이력서 목록 불러오기
  const getResumeList = () => {
    AxiosInstance
    .get('/resume/list')
    .then((response) => {
      setResumeList(response.data);
    })
    .catch((error) => {
      console.error('Error fetching resume list:', error);
    });
    
  };

  //실행되면 시작하는 기능
  useEffect(() => {
    getResumeList();
  }, []);

  //currentPage에 따른 resumeList 리렌더링
  useEffect(() => {
    const startIdx = (currentPage - 1) * maxRenderCount;
    const endIdx = startIdx + maxRenderCount;
    const slicedResumeList = resumeList.slice(startIdx, endIdx);
    setSlicedResumeList(slicedResumeList);
}, [currentPage, resumeList]);

  //이력서를 누르면 modal로 이력서를 불러오는 기능
  const openButton = (resumeNum) => {
    setResumeData((prev) => ({
      ...prev,
      resumeNum: resumeNum,
    }), setModalIsOpen(!modalIsOpen));

  };
  if(!isLogin) {
    return <Navigate to="/signIn" replace={true} />
  } else{
    return (
      <div className={styles.Main}>
        <GNB />
        <div className={styles.Container}>
          <h2>이력서 관리</h2>
          <div className={styles.Section}>
            <Button onClick={() => movePage('/selectResume')} color="secondary">
            Ai인터뷰 하러가기
          </Button>
            
          <div className={styles.ResumeContainer}>
                  {slicedResumeList.map((item, index) => (
                      <section key={item.resumeNum}>
                        <hr />
                        <h4 onClick={() => openButton(item.resumeNum)}>{item.resumeName}</h4>
                        <hr />
                      </section>
                  ))}
          </div>
          </div>
              <div className={styles.ExtraDiv}>
                    <Button
                        className={styles.Button}
                        onClick={() => prevButton()}
                        color="secondary"
                        disabled={prevDisabled}>
                        이전
                    </Button>
                    <StepDot maxPage={maxPage} currentPage={currentPage} size="lg" />
                    <Button
                        className={styles.Button}
                        onClick={() => nextButton()}
                        color="primary"
                        disabled={nextDisabled}>
                        다음
                    </Button>
                </div>
          <div className={styles.Section}>
          <Button onClick={() => addButton()} color="secondary">
              이력서 추가
              </Button>
            </div>      
                
          <div>
            {modalIsOpen && <Resume isViewInterviewList={isViewInterviewList} isView={isView} setResumeData={setResumeData} getResumeList={getResumeList} modalIsOpen={modalIsOpen} setModalIsOpen={setModalIsOpen} resumeData={resumeData}/>}
          </div>
        </div>
      </div>
    );
                  }
  };
  
  export default ResumeManage;