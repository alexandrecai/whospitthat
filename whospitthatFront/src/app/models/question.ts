import {QuestionType} from './questionType';

export class Question{
  idQuestion!: string;
  questionText!: string;
  answers!: string[];
  goodAnswer!: string;
  mediaUrl?: string;
  questionType!: QuestionType;

  constructor(idQuestion: string,questionText: string, answers:string[], goodAnswer: string, mediaUrl: string, questionType: QuestionType) {
    this.idQuestion = idQuestion;
    this.questionText = questionText;
    this.answers = answers;
    this.goodAnswer = goodAnswer;
    this.mediaUrl = mediaUrl;
    this.questionType = questionType;
  }

}
