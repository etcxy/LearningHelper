package com.lh.learninghelper.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lh.learninghelper.R;
import com.lh.learninghelper.bean.AnwerInfo;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private AnwerInfo.DataBean.SubDataBean subDataBean;
    private View view;


    public ReadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReadFragment newInstance(AnwerInfo.DataBean.SubDataBean subDataBean) {
        ReadFragment fragment = new ReadFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, subDataBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subDataBean = (AnwerInfo.DataBean.SubDataBean) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_read, container, false);

        initView();
        // Inflate the layout for this fragment
        return view;
    }

    private void initView() {

        StringBuffer sb = new StringBuffer();

        TextView tv_question = (TextView) view.findViewById(R.id.tv_question);
        final TextView tv_explain = view.findViewById(R.id.tv_explain);
        TextView tv_type = view.findViewById(R.id.tv_types);
        final Button btn_explain = view.findViewById(R.id.btn_explain);

        tv_type.setText(subDataBean.getTitle());
        tv_explain.setText(subDataBean.getExplain());


        sb.append(subDataBean.getQuestionid()+". ");
        sb.append(subDataBean.getQuestion());
        if (subDataBean.getOptiona() == null) {
            sb.append(subDataBean.getOption());
        }else{
            sb.append(subDataBean.getOptiona());
            sb.append(subDataBean.getOptionb());
            sb.append(subDataBean.getOptionc());
            sb.append(subDataBean.getOptiond());
        }

        tv_question.setText(sb.toString());


        btn_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = tv_explain.getVisibility();
                if (visibility == View.GONE) {
                    tv_explain.setVisibility(View.VISIBLE);
                    btn_explain.setText("显示答案");
                }else {
                    tv_explain.setVisibility(View.GONE);
                    btn_explain.setText("隐藏答案");
                }
            }
        });

//        tv_question.setText(
//                subDataBean.getQuestionid() + ". " + subDataBean.getQuestion()
//                        + "\n\nA." + subDataBean.getOptiona()
//                        + "\nB." + subDataBean.getOptionb()
//                        + "\nC." + subDataBean.getOptionc()
//                        + "\nD." + subDataBean.getOptiond()
//                        + "\n\n\n答案解析：" + subDataBean.getExplain()
//        );
    }

}
