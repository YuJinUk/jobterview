from sklearn.feature_extraction.text import CountVectorizer
import pandas as pd
import numpy as np

def wordcheck(txt):

    corpus = []
    corpus.append(txt)

    savlist = ['많이', '좋은', '똑같이']
    rmlst = ['보다', '입니다', '한다고', '합니다', '에서의', '에서는', '이다', '하며', '만큼', '이고', '하는', '보다는',
            '이라는', '했습니다', '에게', '으로', '하고', '에서', '도', '을', '를', '이', '가', '은', '는', '와', '의', '에', '과', '.', ',']
    corpus[0] = corpus[0].split(' ')

    for idx, word in enumerate(corpus[0]):
        if word in savlist:
            corpus[0][idx] = word
            continue
        for rm in rmlst:
            if word[-len(rm):] == rm:
                word = word[:-len(rm)]
                corpus[0][idx] = word
                break
    corpus[0] = ' '.join(corpus[0])

    vect = CountVectorizer()
    document_term_matrix = vect.fit_transform(corpus)       # 문서-단어 행렬 

    tf = pd.DataFrame(document_term_matrix.toarray(), columns=vect.get_feature_names_out())           # TF (Term Frequency)


    D = len(tf)
    df = tf.astype(bool).sum(axis=0)
    idf = np.log((D+1) / (df+1)) + 1             # IDF (Inverse Document Frequency)

    # TF-IDF (Term Frequency-Inverse Document Frequency)
    tfidf = tf * idf                      
    tfidf = tfidf / np.linalg.norm(tfidf, axis=1, keepdims=True)

    minval = tfidf.iloc[0].min()
    maxval = tfidf.iloc[0].max()
    maxcnt = int(maxval/minval)
    count_val = {'maxcnt' : [],
                 'maxnum' : maxcnt}
    maxword = {f'가장 많은 횟수인 {int(maxval/minval)}번 사용한 단어는 다음과 같아요' : []}
    for i in tfidf:
        check = tfidf[i][0]
        x = int(check / minval)
        count_val[i] = x
        if check / maxval == 1.0:
            count_val['maxcnt'].append(i)
        
    return count_val