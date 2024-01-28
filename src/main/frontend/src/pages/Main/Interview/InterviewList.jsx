import React, { useEffect, useRef, useState } from "react";

import styles from "./Interview.module.scss";
import { Button } from 'reactstrap';
import { useNavigate, Navigate } from "react-router-dom";
import { useRecoilState } from 'recoil';
import {interviewDataState, isLoginState} from '../../../recoil/RecoilState'
import StepDot from '../../../components/StepDot/StepDot';
import cn from "classnames";
import { BiSolidCopy } from 'react-icons/bi';
import GNB from "../../../components/GNB/GNB";

const InterviewList = () => {
    const navigate = useNavigate();
  
    const movePage = (url) => {
      navigate(url)
    };

    const [isLogin, setIsLogin] = useRecoilState(isLoginState);

    //interviewData를 담은 RecoilState
    const [interviewData, setInterviewData] = useRecoilState(interviewDataState);
    //필요한만큼 자른 interviewData를 담는 state
    const [slicedInterviewData, setSlicedInterviewData] = React.useState([]);

    //page 관리
    const [currentPage, setCurrentPage] = React.useState(1);
    const maxRenderCount = 5;
    const maxPage =  Math.ceil(interviewData.length / maxRenderCount);
    const prevDisabled = (currentPage == 1);
    const nextDisabled = (currentPage == maxPage);
  

    /* 질문의 블러처리 된 답변을 클릭 시 블러 On/Off하는 이벤트 */
    const [isBlurOn, setIsBlurOn] = React.useState(Array(maxRenderCount).fill(true));

    const answerClick = index => {
        setIsBlurOn(prevState => {
            const newState = [...prevState];
            newState[index] = !prevState[index];
            return newState;
        })
    }   

    const prevButton = () => {
        setIsBlurOn(Array(maxRenderCount).fill(true));
        setCurrentPage(currentPage-1);
    }

    const nextButton = () => {
        setIsBlurOn(Array(maxRenderCount).fill(true));
        setCurrentPage(currentPage+1);
    }

    /* 답변 내용 복사 이벤트 */
    const refs = useRef([]);

    const copyClick = (element) => {
        const text = element.innerText;

        navigator.clipboard.writeText(text)
        .then(() => {
            alert("복사되었습니다.");
        })
        .catch(() => {
            alert("복사에실패하였습니다.");
        })
    }

    //currentPage에 따른 질문과 답 리렌더링
    useEffect(() => {
        const startIdx = (currentPage - 1) * maxRenderCount;
        const endIdx = startIdx + maxRenderCount;
        const slicedInterviewData = interviewData.slice(startIdx, endIdx);
        setSlicedInterviewData(slicedInterviewData);
    }, [currentPage, interviewData]);

    if(!isLogin) {
        return <Navigate to="/signIn" replace={true} />
      } else{
    return (
        <div className={styles.Main}>
            <GNB />
            <div className={styles.Container}>
                <div className={styles.Section}>
                    <div className={styles.InterviewCheckContainer}>
                        <div>
                            {slicedInterviewData.map((item, index) => (
                                <section className={styles.Form} key={item.interivewNumber}>
                                <h4>{item.interviewQuestion}</h4>
                                <p id={'id' + item.interivewNumber} ref={preRef => refs.current[item.interivewNumber] = preRef} onClick={() => answerClick(index)} className={cn({[styles.Blur] : isBlurOn[index]}, [styles.Answer])}>
                                    {item.interviewAnswer}
                                </p>
                                <BiSolidCopy onClick={() => copyClick(refs.current[item.interivewNumber])}/>
                                </section>)
                            )}
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
                <Button
                        onClick={() => movePage('/resumeList')}
                        color="secondary">
                        이력서 목록
                </Button>
                </div>
            </div>
        </div>
    )}
}

export default InterviewList;