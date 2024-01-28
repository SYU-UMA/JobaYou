import React, { useEffect, useRef, useState } from "react";
import styles from "./Interview.module.scss";
import { Button } from 'reactstrap';
import { useNavigate, Navigate } from "react-router-dom";
import { useRecoilState } from 'recoil';
import {interviewDataState, interviewTypeState, isLoginState} from '../../../recoil/RecoilState'
import StepDot from '../../../components/StepDot/StepDot';
import cn from "classnames";
import { isLogin } from '../../../storage/Cookie';
import { BiSolidCopy } from 'react-icons/bi';
import AxiosInstance from "../../../api/AxiosInstance";
import axios from "axios";
import GNB from "../../../components/GNB/GNB";
const ExInterviewList = () => {
    const navigate = useNavigate();
  
    const movePage = (url) => {
      navigate(url)
    };

    //interviewType을 담은 RecoilState
    const [interviewType, setInterviewType] = useRecoilState(interviewTypeState);
    //interviewData를 담은 state
    const [interviewData, setInterviewData] = React.useState([]);
    
    //page 관리
    const [currentPage, setCurrentPage] = React.useState(1);
    
    /* 질문의 블러처리 된 답변을 클릭 시 블러 On/Off하는 이벤트 */
    const [isBlurOn, setIsBlurOn] = React.useState(Array(5).fill(true));

    const answerClick = index => {
        setIsBlurOn(prevState => {
            const newState = [...prevState];
            newState[index] = !prevState[index];
            return newState;
        })
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

     //InterviewList를 가져오기 위한 기능
    const getInterview = () => {
        AxiosInstance
        .get('/interview/recentlist/'+interviewType)
        .then((response) => {
        setInterviewData(response.data);
        })
        .then(() => {
        movePage('/exInterviewList');
        })
        .catch((error) => {
        console.log(error);
        });
    }

    
    useEffect(() => {
        getInterview();
    }, [])

    return (
        <div className={styles.Main}>
            <GNB />
            <div className={styles.Container}>
                <div>
                    { interviewType == 'front' && <h2>프론트엔드 개발자 최근 인터뷰</h2>}
                    { interviewType == 'back' && <h2>백엔드 개발자 최근 인터뷰</h2>}
                    { interviewType == 'data' && <h2>데이터 엔지니어 최근 인터뷰</h2>}
                </div>
                <div className={styles.Section}>
                    <div className={styles.InterviewCheckContainer}>
                        {interviewData.map((item, index) => (
                            <section className={styles.Form} key={item.interivewNumber}>
                            <h4>{item.interviewQuestion}</h4>
                            <p id={'id' + item.interivewNumber} ref={preRef => refs.current[item.interivewNumber] = preRef} onClick={() => answerClick(index)} className={cn({[styles.Blur] : isBlurOn[index]}, [styles.Answer])}>
                                {item.interviewAnswer}
                            </p>
                            <BiSolidCopy onClick={() => copyClick(refs.current[item.interivewNumber])}/>
                            </section>)
                        )}
                    </div>
                    <Button
                        onClick={() => movePage('/selectResume')}
                        color="primary">
                        Ai인터뷰 하러가기
                    </Button>
                </div>
            </div>
        </div>
    )
}

export default ExInterviewList;